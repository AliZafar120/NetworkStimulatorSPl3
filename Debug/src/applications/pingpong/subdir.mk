################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/pingpong/pingpong.olg.cpp 

CC_SRCS += \
../src/applications/pingpong/pingpong.cc 

OBJS += \
./src/applications/pingpong/pingpong.o \
./src/applications/pingpong/pingpong.olg.o 

CC_DEPS += \
./src/applications/pingpong/pingpong.d 

CPP_DEPS += \
./src/applications/pingpong/pingpong.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/pingpong/%.o: ../src/applications/pingpong/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/pingpong/%.o: ../src/applications/pingpong/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


