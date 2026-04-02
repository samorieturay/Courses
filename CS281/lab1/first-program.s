.text
    .globl main

main:
    addi  t0, zero, 281 #  t0 = zero + 281. This is a way to move a value
    li    t1, 0x119 # specifiying a hex constant
    li    t2, 0b100011001 # specifying a binary constant
    li    t3, 0431 # specifying an octal constant
    add   t4, zero, zero # This is a way to zero out a register

#now exit
li a0, 10 # Exit code for ecall
ecall