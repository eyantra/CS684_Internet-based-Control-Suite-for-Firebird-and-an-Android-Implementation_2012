# Serial

import serial
import sys
import MySQLdb

def port_init(port = "/dev/ttyUSB0", boad_rate = 9600):
    ser = serial.Serial(port, boad_rate)
    return ser

def status_handle(msg, bot_id):
    db = MySQLdb.connect(host="localhost", user="root", passwd="root", db="embedded")
    cur = db.cursor()
    query = "INSERT INTO messagepass(message,bot_id) VALUES (\"" + msg + "\", " + bot_id + ");"
    cur.execute(query)
    db.commit()
    db.close()
    return

def wait_reply(ser, bot_id):
    resp = ser.read()
    if resp == '#':
        return
    elif resp == '(':
        msg = ""
        while True:
            resp = ser.read()
            if resp == ')':
                status_handle(msg, bot_id)
                return wait_reply(ser, bot_id)
            msg = msg+resp
    print "Wrong format"

def main():
    ser = port_init()
    msg = sys.argv[1]
    bot_id = msg.split('$')[0]
    ser.write(msg)
    wait_reply(ser, bot_id)
    ser.close()
    print '#'

main()
