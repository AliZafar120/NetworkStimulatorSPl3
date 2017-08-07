################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/discovery/discovery.olg.cpp 

CC_SRCS += \
../src/applications/discovery/discovery.cc 

OBJS += \
./src/applications/discovery/discovery.o \
./src/applications/discovery/discovery.olg.o 

CC_DEPS += \
./src/applications/discovery/discovery.d 

CPP_DEPS += \
./src/applications/discovery/discovery.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/discovery/%.o: ../src/applications/discovery/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/discovery/%.o: ../src/applications/discovery/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


