.text
    .globl _start

_start:
    add t0, zero, zero # t0 =zero
    addi t1, zero, 10 # t0 1 tzero+ 10
    li   t2, 0x14  # t2  0x14= 
    add t0, t1, t2 # 00 = t1t2imm
    addi t0, t0, 0b1000110  # t0 = 

li a0, 10 # Exit code for ecall
ecall