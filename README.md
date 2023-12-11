# **Cycle Renting Database System**

Java based renting database system for cycles in a city area (represented as an x-y plane).

## **Structure of records**:

### **Structure of cycleObject Array**:

> [{cycleObject},{cycleObject},...]

### **Structure of cycleObject**

> cycle { id: +ve int, x: +ve int, y: +ve int, hoursRented: +ve int, isRented: boolean }

## **Structure of user Object**:

> User { x: +ve int, y: +ve int, hoursRented: +ve int }

## **Command guide**

Each arg for command name is seperated by a " ", and is referred to as command args.
Each arg for command values is referred to as a value arg.

## **Guide for command args**

Command args are case-insensitive. Three args are not mandatory, simply as many as needed for the command.

Invalid command will return -

> Error 404: Command Not Found.

**Format**: _shorthand-longhand_

### First args:

- h-help
- a-add
- d-delete
- u-update
- g-get
- r-rent

### Second args:

- u-user
- c-cycle

### Third args:

- i-id
- l-location
- b-bill
- r-rented
- h-hoursRented
- a-areaRange
- p-proximity

## **Guide for value args**

Each command asks for values one at a time, simply enter return to leave it empty.

If no matching cycles exist:

- delete commands will return -
  - > 0 Matching Records Deleted
- update commands will return -
  - > No Matching Record exists
- get commands will return -
  - > No Matching Records
- rent commands will return -
  - > No Matching Cycles

Invalid values will return -

> Invalid values: command failed, no changes made.

## Examble of command:

### Longhand

update cycle location⏎
20⏎
30⏎
50⏎
30

### Shorthand

u s l⏎
20⏎
⏎
50⏎
30

## **Commands:**

### Format:

- **Command** `(arg-name: expected type and/or value range (default value))`
  - _what it does._

### **Help:**

- **help** `()`
  - _returns a list of all commands._

### **On start:**

- **intial user location** `(x-value: +ve int (0), y-value: +ve int(0))`

  - _asks x & y coords of user on launch, is 0,0 by default._

- **rent per hour** `(rent per hour: +ve non-zero float (0.5))`
  - _asks for rent per hour rate_

### **Adding/deleting records:**

- **add cycle** `(x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0))`

  - _adds record with given values._

- **delete cycle** `(cycleId1: +ve int(required), cyceId2:+ve int (cycleId1))`

  - _deletes cycles in inclusive range(Id1, Id2), confirms number of successfully deleted records, if cycleId1 is returned empty the command below is implemented._

- **delete cycle** `(x-value: +ve int(0), y-value: +ve (0), arSide: +ve int(1), arSide2: +ve int(arSide))`

  - _delete records in rectangle of length = arSide, breadth = arSide2 & bottom-left-corner= x,y._

- **delete cycle area** `(/ same as 1st above/ , /same as 2nd above/ , x range: +ve int(0), y range: +ve int (x))`
  - _delete records in a rectangle of sides (x range*2)-1,(y range*2 -1) range centered at x,y_

### **Updating records:**

- **update cycle** `(cycleId: +ve int (required), range end cycleId : +ve int >= cycleId (cycleId), x-value: +ve int (unchanged), y-value: +ve int(unchanged), mark as not rented? : boolean (false))`

  - _updates locations of all rented cycles in inclusive range and can set rented status of all to false_

- **update cycle location** `(cycleId: +ve int (required), x-value: +ve int (unchanged), y-value: +ve int(unchanged))`

  - _updates cycle location by Id._

- **update cycle rented** `(cycleId: +ve int (required), range end cycleId : +ve int >= cycleId (cycleId))`

  - _updates the rented status of all rented cycles in inclusive range to false_

- **update user location** `(x-value: +ve int (0), y-value: +ve int(0))`
  - _as named._

### **Requesting records:**

- **get user** `(ratePerHour in euro : +ve non-zero float (0.5))`

  - _returns user location, rented cycles, total rented hours & total spending in euro._

- **get user location** `()`

  - _returns user location_

- **get user hoursRented** `()`

  - _returns users total rented hours_

- **get user bill** `(ratePerHour in euro : +ve non-zero float (0.5))`

  - _returns user total spending_

- **get cycle** `()`

  - _returns all records._

- **get cycle rented** `()`

  - _returns all rented cycles_

- **get cycle id** `(cycleId: +ve int(required), OptionalcycleId: +ve int (cycleId), include isRented : boolean (false))`

  - _returns records by id range, range inclusive._

- **get cycle hoursRented** `(maxhoursRented: +ve int (unlimited), include isRented : boolean (false), minhoursRented: +ve int <= maxhoursRented (0))`

  - _returns records by rented hours, range inclusive._

- **get cycle proximity** `(range x: +ve int (5), range y: +ve int (x), include isRented : boolean (false))`
  - _returns records by distance from user._

### **Renting:**

- **rent cycle** `(cycleId: +ve int (required), number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int < 25 (1), rate per hour: +ve float (0.5))`

  - _rents every available cycle with cycleId >= given id till required number of cycles are marked for rent. if not enough, available cycles are marked for rent. bill is calculated, confirmation is asked_

- **rent cycle location** `(number of cycles : +ve nono-zero int (1), hours to rent: +ve non-zero int < 25 (1), x-value: +ve int(0), y-value: +ve (0), rate per hour: +ve float (0.5))`

  - _rents available cycles by location, if enough cycles, calculates bill and asks for confirmation, if not enough cycles presents invoice for max cycles available & asks for confirmation, if more cycles than needed at location lowest id's are rented_

- **rent cycle proximity** `(number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int < 25 (1), range x: +ve int (5), range y: +ve int (5), rate per hour: +ve float (0.5))`

  - _rents available cycles by proximity to user location, handles excess, matching number and scarcity of cycles as in previous command_

- **rent cycle area** `(number of cycles : +ve non-zero int (1), hours to rent: +ve non-zero int < 25 (1), x-value: +ve int(0), y-value: +ve (0), range x: +ve int (5), range y: +ve int (5), rate per hour: +ve float (0.5))`
  - _rents available cycles by proximity to given location, handles excess, matching number & scarcity of cycles as in previous command_
