################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../scratch/pktfwd-prov-test.cc \
../scratch/simple.cc 

OBJS += \
./scratch/pktfwd-prov-test.o \
./scratch/simple.o 

CC_DEPS += \
./scratch/pktfwd-prov-test.d \
./scratch/simple.d 


# Each subdirectory must supply rules for building sources it contributes
scratch/%.o: ../scratch/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


