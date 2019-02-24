# Simple Checkout

## Dependencies
 - Java 8
 - Maven

## Test input

| SKU     | Name        | Price    |
| --------|:-----------:| --------:|
| ipd     | Super iPad  | $549.99  |
| mbp     | MacBook Pro | $1399.99 |
| atv     | Apple TV    | $109.50  |
| vga     | VGA adapter | $30.00   |

## Testing scenarios

#### Location
src/test/java/au/com/dius/challenge/ExamplesScenariosTest.java

#### Example scenarios
-----------------
- we're going to have a 3 for 2 deal on Apple TVs. For example, if you buy 3 Apple TVs, you will pay the price of 2 only
```
SKUs Scanned: atv, atv, atv, vga
Total expected: $249.00
```
- the brand new Super iPad will have a bulk discounted applied, where the price will drop to $499.99 each, if someone buys more than 4
```
SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd
Total expected: $2718.95
```
- we will bundle in a free VGA adapter free of charge with every MacBook Pro sold
```
SKUs Scanned: mbp, vga, ipd
Total expected: $1949.98
```
