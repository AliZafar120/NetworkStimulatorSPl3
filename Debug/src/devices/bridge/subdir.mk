################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/devices/bridge/bridge-channel.cc \
../src/devices/bridge/bridge-net-device.cc 

OBJS += \
./src/devices/bridge/bridge-channel.o \
./src/devices/bridge/bridge-net-device.o 

CC_DEPS += \
./src/devices/bridge/bridge-channel.d \
./src/devices/bridge/bridge-net-device.d 


# Each subdirectory must supply rules for building sources it contributes
src/devices/bridge/%.o: ../src/devices/bridge/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


