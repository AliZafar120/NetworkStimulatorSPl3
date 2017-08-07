################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../samples/main-attribute-value.cc \
../samples/main-callback.cc \
../samples/main-grid-topology.cc \
../samples/main-ns2-mob.cc \
../samples/main-packet-header.cc \
../samples/main-packet-printer.cc \
../samples/main-packet-tag.cc \
../samples/main-propagation-loss.cc \
../samples/main-ptr.cc \
../samples/main-random-topology.cc \
../samples/main-random-variable.cc \
../samples/main-random-walk.cc \
../samples/main-simple.cc \
../samples/main-simulator.cc \
../samples/main-test-sync.cc \
../samples/main-test.cc \
../samples/main-trace.cc \
../samples/main-tw.cc 

OBJS += \
./samples/main-attribute-value.o \
./samples/main-callback.o \
./samples/main-grid-topology.o \
./samples/main-ns2-mob.o \
./samples/main-packet-header.o \
./samples/main-packet-printer.o \
./samples/main-packet-tag.o \
./samples/main-propagation-loss.o \
./samples/main-ptr.o \
./samples/main-random-topology.o \
./samples/main-random-variable.o \
./samples/main-random-walk.o \
./samples/main-simple.o \
./samples/main-simulator.o \
./samples/main-test-sync.o \
./samples/main-test.o \
./samples/main-trace.o \
./samples/main-tw.o 

CC_DEPS += \
./samples/main-attribute-value.d \
./samples/main-callback.d \
./samples/main-grid-topology.d \
./samples/main-ns2-mob.d \
./samples/main-packet-header.d \
./samples/main-packet-printer.d \
./samples/main-packet-tag.d \
./samples/main-propagation-loss.d \
./samples/main-ptr.d \
./samples/main-random-topology.d \
./samples/main-random-variable.d \
./samples/main-random-walk.d \
./samples/main-simple.d \
./samples/main-simulator.d \
./samples/main-test-sync.d \
./samples/main-test.d \
./samples/main-trace.d \
./samples/main-tw.d 


# Each subdirectory must supply rules for building sources it contributes
samples/%.o: ../samples/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


