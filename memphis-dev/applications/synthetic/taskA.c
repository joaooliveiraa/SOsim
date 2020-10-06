#include <api.h>
#include <stdlib.h>

#include "syn_std.h"

//MEMPHIS message structure
Message msg1;
Message msg2;

void main()
{
    Echo("Task A started at time ");
	Echo(itoa(GetTick()));

	for(int i=0;i<SYNTHETIC_ITERATIONS;i++)
	{
		//Compute and send something
		compute(&msg1.msg);
		msg1.length = 128;
		Send(&msg1,taskB);

		//Compute and send something
		compute(&msg2.msg);
		msg2.length = 128;
		Send(&msg2,taskC);
	}

    Echo("Task A finished at time");
    Echo(itoa(GetTick()));
	exit();
}
