#include "func1.h"

//#ifndef CONDITIONAL_H_
//#define CONDITIONAL_H_

#pragma once //ensures that file is included once only... non-standard

//#ifdef FOO
#if defined(FOO) //alternate way to write previous line

#define NUMBER 23

#else

#define FOO 

#endif


#endif
