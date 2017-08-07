################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/mincost-query/mincost-query.olg.cpp 

CC_SRCS += \
../src/applications/mincost-query/mincost-query.cc 

OBJS += \
./src/applications/mincost-query/mincost-query.o \
./src/applications/mincost-query/mincost-query.olg.o 

CC_DEPS += \
./src/applications/mincost-query/mincost-query.d 

CPP_DEPS += \
./src/applications/mincost-query/mincost-query.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/mincost-query/%.o: ../src/applications/mincost-query/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/mincost-query/%.o: ../src/applications/mincost-query/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


