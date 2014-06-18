
/********************************************************************************

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in
the documentation and/or other materials provided with the
distribution.

* Neither the name of the copyright holders nor the names of
contributors may be used to endorse or promote products derived
from this software without specific prior written permission.

* Source code can be used for academic purpose.
For commercial use permission form the author needs to be taken.

Software released under Creative Commence cc by-nc-sa licence.
For legal information refer to:
http://creativecommons.org/licenses/by-nc-sa/3.0/legalcode

********************************************************************************/


#include<avr/io.h>
#include<avr/interrupt.h>
#include<util/delay.h>
#include<stdlib.h>

unsigned char data; //to store received data from UDR1

void buzzer_pin_config (void)
{
 DDRC = DDRC | 0x08;	//Setting PORTC 3 as outpt
 PORTC = PORTC & 0xF7;	//Setting PORTC 3 logic low to turnoff buzzer
}

void motion_pin_config (void)
{
 DDRA = DDRA | 0x0F;
 PORTA = PORTA & 0xF0;
 DDRL = DDRL | 0x18; //Setting PL3 and PL4 pins as output for PWM generation
 PORTL = PORTL | 0x18; //PL3 and PL4 pins are for velocity control using PWM.
}

//Function to initialize ports
void port_init()
{
motion_pin_config();
buzzer_pin_config();
}

void buzzer_on (void)
{
 unsigned char port_restore = 0;
 port_restore = PINC;
 port_restore = port_restore | 0x08;
 PORTC = port_restore;
}

void buzzer_off (void)
{
 unsigned char port_restore = 0;
 port_restore = PINC;
 port_restore = port_restore & 0xF7;
 PORTC = port_restore;
}

//Function To Initialize UART0
// desired baud rate:9600
// actual baud rate:9600 (error 0.0%)
// char size: 8 bit
// parity: Disabled
void uart0_init(void)
{
 UCSR0B = 0x00; //disable while setting baud rate
 UCSR0A = 0x00;
 UCSR0C = 0x06;
 UBRR0L = 0x5F; //set baud rate lo
 UBRR0H = 0x00; //set baud rate hi
 UCSR0B = 0x98;
}

char fcall[5][5];
int i =0, j = 0;
int botId;


// Pass the message to be passed as a string
void send_status(char msg[])
{
	int i;
	UDR0 = 0x28;
	for(i = 0; msg[i]!=0; i++) {
		while ( !( UCSR0A & (1<<UDRE0)) );
		UDR0 = msg[i];
	}
	while ( !( UCSR0A & (1<<UDRE0)) );
	UDR0 = 0x29;
}

void forward(int n)
{
	char msg[] = "Forward Half Done";

	PORTA = 0x06; //forward
	_delay_ms(n*50);
	send_status(msg);
	_delay_ms(n*50);
	PORTA = 0x00; //stop
}

void backward(int n)
{
	PORTA = 0x09;
	_delay_ms(n*100);
	PORTA = 0x00;
}

void left(int n)
{
	PORTA = 0x05;
	_delay_ms(n*100);
	PORTA = 0x00;
}

void right(int n)
{
	PORTA = 0x0A;
	_delay_ms(n*100);
	PORTA = 0x00;
}


void function_caller()
{
	int val, par;
	val = atoi(fcall[1]);
	par = atoi(fcall[2]);
	switch(val) {
		case 1 : forward(par); break;
		case 2 : backward(par); break;
		case 3 : left(par); break;
		case 4 : right(par); break;
		default: UDR0 = 0x26;
	}
}

//FORMAT "botId$funCode$par1$par2$par3#"
SIGNAL(SIG_USART0_RECV)
{
	cli();
	data = UDR0;

	if(data == 0x23) // #
	{
		if(atoi(fcall[0]) == botId) {
			if(j != 0) {
				fcall[i][j] = 0;
				sei();
				function_caller();
				cli();
			}
			UDR0 = data;
		}
		i = 0;
		j = 0;
	}
	else if(data == 0x24) // $
	{
		fcall[i][j] = 0;
		i++;
		j = 0;
	}
	else
	{	
		fcall[i][j] = data;
		j++;
	}
	sei();
}


//Function To Initialize all The Devices
void init_devices()
{
 cli(); //Clears the global interrupts
 port_init(); //Initializes all the ports
 uart0_init(); //Initailize UART1 for serial communiaction
 sei(); //Enables the global interrupts
}

//Main Function
int main(void)
{
	/* Set your unique botId to this variable */
	botId = 9;
	init_devices();
	while(1);
}

