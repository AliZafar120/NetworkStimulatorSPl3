################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/pingpong-l4/pingpong-l4.olg.cpp 

CC_SRCS += \
../src/applications/pingpong-l4/pingpong-l4.cc 

OBJS += \
./src/applications/pingpong-l4/pingpong-l4.o \
./src/applications/pingpong-l4/pingpong-l4.olg.o 

CC_DEPS += \
./src/applications/pingpong-l4/pingpong-l4.d 

CPP_DEPS += \
./src/applications/pingpong-l4/pingpong-l4.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/pingpong-l4/%.o: ../src/applications/pingpong-l4/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/pingpong-l4/%.o: ../src/applications/pingpong-l4/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


