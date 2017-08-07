################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/routing/static-routing/ipv4-routing-table-entry.cc \
../src/routing/static-routing/ipv4-static-routing.cc 

OBJS += \
./src/routing/static-routing/ipv4-routing-table-entry.o \
./src/routing/static-routing/ipv4-static-routing.o 

CC_DEPS += \
./src/routing/static-routing/ipv4-routing-table-entry.d \
./src/routing/static-routing/ipv4-static-routing.d 


# Each subdirectory must supply rules for building sources it contributes
src/routing/static-routing/%.o: ../src/routing/static-routing/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


