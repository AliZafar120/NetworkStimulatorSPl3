################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/l4-platform/l4-device.cc \
../src/l4-platform/l4-platform-helper.cc \
../src/l4-platform/tcp-transport-socket-factory-impl.cc \
../src/l4-platform/tcp-transport-socket-impl.cc \
../src/l4-platform/transport-select.cc \
../src/l4-platform/transport-socket.cc \
../src/l4-platform/udp-transport-socket-factory-impl.cc \
../src/l4-platform/udp-transport-socket-impl.cc 

OBJS += \
./src/l4-platform/l4-device.o \
./src/l4-platform/l4-platform-helper.o \
./src/l4-platform/tcp-transport-socket-factory-impl.o \
./src/l4-platform/tcp-transport-socket-impl.o \
./src/l4-platform/transport-select.o \
./src/l4-platform/transport-socket.o \
./src/l4-platform/udp-transport-socket-factory-impl.o \
./src/l4-platform/udp-transport-socket-impl.o 

CC_DEPS += \
./src/l4-platform/l4-device.d \
./src/l4-platform/l4-platform-helper.d \
./src/l4-platform/tcp-transport-socket-factory-impl.d \
./src/l4-platform/tcp-transport-socket-impl.d \
./src/l4-platform/transport-select.d \
./src/l4-platform/transport-socket.d \
./src/l4-platform/udp-transport-socket-factory-impl.d \
./src/l4-platform/udp-transport-socket-impl.d 


# Each subdirectory must supply rules for building sources it contributes
src/l4-platform/%.o: ../src/l4-platform/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


