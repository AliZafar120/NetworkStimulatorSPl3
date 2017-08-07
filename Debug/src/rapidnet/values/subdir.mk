################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/rapidnet/values/bool-value.cc \
../src/rapidnet/values/byte-array-value.cc \
../src/rapidnet/values/id-value.cc \
../src/rapidnet/values/int32-value.cc \
../src/rapidnet/values/ipv4-value.cc \
../src/rapidnet/values/list-value.cc \
../src/rapidnet/values/nil-value.cc \
../src/rapidnet/values/real-value.cc \
../src/rapidnet/values/str-value.cc \
../src/rapidnet/values/sv-value.cc \
../src/rapidnet/values/type-ids.cc \
../src/rapidnet/values/value.cc 

OBJS += \
./src/rapidnet/values/bool-value.o \
./src/rapidnet/values/byte-array-value.o \
./src/rapidnet/values/id-value.o \
./src/rapidnet/values/int32-value.o \
./src/rapidnet/values/ipv4-value.o \
./src/rapidnet/values/list-value.o \
./src/rapidnet/values/nil-value.o \
./src/rapidnet/values/real-value.o \
./src/rapidnet/values/str-value.o \
./src/rapidnet/values/sv-value.o \
./src/rapidnet/values/type-ids.o \
./src/rapidnet/values/value.o 

CC_DEPS += \
./src/rapidnet/values/bool-value.d \
./src/rapidnet/values/byte-array-value.d \
./src/rapidnet/values/id-value.d \
./src/rapidnet/values/int32-value.d \
./src/rapidnet/values/ipv4-value.d \
./src/rapidnet/values/list-value.d \
./src/rapidnet/values/nil-value.d \
./src/rapidnet/values/real-value.d \
./src/rapidnet/values/str-value.d \
./src/rapidnet/values/sv-value.d \
./src/rapidnet/values/type-ids.d \
./src/rapidnet/values/value.d 


# Each subdirectory must supply rules for building sources it contributes
src/rapidnet/values/%.o: ../src/rapidnet/values/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


