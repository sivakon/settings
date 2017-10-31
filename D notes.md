# Notes on D

## Linking C with D

C file - arrc.c:
```
#include <stdio.h>
void printThreeInts(int *ints) {
  int i;
  for(i=0; i<3; ++i)
    printf("%i\n", ints[i]);
}
```

D file - arr.d
```
extern(C) @nogc nothrow void printThreeInts(int*);
void main() {
  int[] ints = [1, 2, 3];
  printThreeInts(cast(int*)ints);
  printThreeInts(ints.ptr);
}
struct IntArray {
  size_t length;
  int* ptr;
}
auto ia = IntArray(ints.length, ints.ptr);
auto pi = cast(int*)ia;
```


How to compile!!
```
gcc -c arrc.c
dmd arr.d arrc.o
```


[Link to understand linking C with D](https://www.safaribooksonline.com/library/view/learning-d/9781783552481/ch09s03.html)