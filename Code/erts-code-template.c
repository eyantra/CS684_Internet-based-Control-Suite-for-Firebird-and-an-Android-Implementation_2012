
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
 UBRR0L = 0x5F; //set baud rate lo - Change to 0x47 if Firebird Frequency is 11059200Hz and not 14745600 Hz
 UBRR0H = 0x00; //set baud rate hi
 UCSR0B = 0x98;
}

/* If any of the function needs more than 5 parameters or parameters with more than 5 characters each, modify this declaration */
char fcall[5][5]; //The array in which message values are populated
int i =0, j = 0; // Counters to keep track of array index
int botId; //The unique botId for the robot
unsigned char data; //to store received data

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

// USER CODE STARTS

/* 
Add all the functions required by your project here. The port and device initialization function, the task functions, other helper functions that may be needed and so on. 
*/


// PROJECT_CODE















// USER CODE ENDS


/*
Modify the switch case part to suit your project. If there are more functions then add new cases and register them as functions in the server. The case id should be the functionId used for registration. For each case, call with the required functions. If the number of parameters required are more, then modify the variable declarations and parI initialization statements accordingly. parI needn't be an integer, they can be converted to any datatypes as required.

If more than 5 parameters are needed or if any parameters is longer than 5 characters, then increase the size of fcall array as required.
*/
void function_caller()
{
	int val, par1, par2;
	val = atoi(fcall[1]);
	switch(val) {
		case 1 : par1 = atoi(fcall[2]);
			 par2 = atoi(fcall[3]);
			 my_function1(par1,par2);
			 break;
		case 2 : par1 = atoi(fcall[2]);
			 my_function2(par1);
			 break;

		default: UDR0 = 0x26;
	}
}

/*
Signal handler for input through XBee module
The input ASCII value are copied to the array fcall[i][j], where i is the the number of '$' seen till that point and j is the number of characters seen after the last '$'. So at the end of the message, it will be as if the message has been parsed as botId, functionId, par1, par2, etc., into the array sequentially.

'#' refers to the end of the command. When '#' is seen, it is verified whether the botId of message and botId of the bot are the same. If they are, then function_caller() is called to handle this message. Else, the message is rejected by setting i and j to 0 and the wait for next message starts.

MESSAGE FORMAT - "botId$funCode$par1$par2$par3#"
*/
SIGNAL(SIG_USART0_RECV)
{
	cli(); // Disable all interrupts
	data = UDR0; //Reads the current ASCII value

	if(data == 0x23) // # - End of message
	{
		if(atoi(fcall[0]) == botId) {
			if(j != 0) {
				fcall[i][j] = 0;
				sei(); //Enable all interrupts
				function_caller();
				cli(); // Disable all interrupts
			}
			UDR0 = data; // Write back '#' to the XBee module when a request has been handled
		}
		i = 0;
		j = 0;
	}
	else if(data == 0x24) // $ - End of a particular value
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
	sei(); //Enable all interrupts
}

//Function To Initialize all The Devices
void init_devices()
{
 cli(); //Clears the global interrupts
 uart0_init(); //Initailize UART1 for serial communiaction

 myport1_init(); 
 //Call all the port or device initialization functions needed.
 mydevice1_init();

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

