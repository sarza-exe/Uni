%include        'functions.asm'

SECTION .data
msg1        db      'Podaj liczbe: ', 0h
msg2        db      'Liczba cyfr wynosi: ', 0h

SECTION .bss
innumber:   resb    255

SECTION .text
global _start

_start:
    mov     eax, msg1
    call    sprint 
    
    mov     edx, 255        ;buffer size
    mov     ecx, innumber
    mov     ebx, 0          ;STDIN
    mov     eax, 3          ;read
    int     0x80

    mov     eax, innumber
    sub     ebx, ebx

nextdigit:
    cmp     byte [eax], 10      ; 10 to znak nowej lini
    jz      continue
    sub     edx, edx
    mov     dl, byte [eax]      ; dl is last 8 bits of edx
    add     ebx, edx
    sub     ebx, 48              ; ebx
    inc     eax
    jmp     nextdigit

continue:
    mov     eax, msg2
    call    sprint

    mov     eax, ebx
    call    iprintLF

    call    quit