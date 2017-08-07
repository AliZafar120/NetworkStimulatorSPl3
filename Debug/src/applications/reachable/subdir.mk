################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/reachable/reachable.olg.cpp 

CC_SRCS += \
../src/applications/reachable/reachable.cc 

OBJS += \
./src/applications/reachable/reachable.o \
./src/applications/reachable/reachable.olg.o 

CC_DEPS += \
./src/applications/reachable/reachable.d 

CPP_DEPS += \
./src/applications/reachable/reachable.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/reachable/%.o: ../src/applications/reachable/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/reachable/%.o: ../src/applications/reachable/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


