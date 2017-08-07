################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/rapidnet/tests/aggwrap-test.cc \
../src/rapidnet/tests/blowfish-encryption-test.cc \
../src/rapidnet/tests/database-test.cc \
../src/rapidnet/tests/evp-key-test.cc \
../src/rapidnet/tests/expression-test.cc \
../src/rapidnet/tests/functions-test.cc \
../src/rapidnet/tests/header-test.cc \
../src/rapidnet/tests/heap-relation-test.cc \
../src/rapidnet/tests/join-project-test.cc \
../src/rapidnet/tests/pki-authentication-test.cc \
../src/rapidnet/tests/relation-test.cc \
../src/rapidnet/tests/temp-relation-test.cc \
../src/rapidnet/tests/tuple-attribute-test.cc \
../src/rapidnet/tests/tuple-test.cc \
../src/rapidnet/tests/utils-test.cc \
../src/rapidnet/tests/values-test.cc 

OBJS += \
./src/rapidnet/tests/aggwrap-test.o \
./src/rapidnet/tests/blowfish-encryption-test.o \
./src/rapidnet/tests/database-test.o \
./src/rapidnet/tests/evp-key-test.o \
./src/rapidnet/tests/expression-test.o \
./src/rapidnet/tests/functions-test.o \
./src/rapidnet/tests/header-test.o \
./src/rapidnet/tests/heap-relation-test.o \
./src/rapidnet/tests/join-project-test.o \
./src/rapidnet/tests/pki-authentication-test.o \
./src/rapidnet/tests/relation-test.o \
./src/rapidnet/tests/temp-relation-test.o \
./src/rapidnet/tests/tuple-attribute-test.o \
./src/rapidnet/tests/tuple-test.o \
./src/rapidnet/tests/utils-test.o \
./src/rapidnet/tests/values-test.o 

CC_DEPS += \
./src/rapidnet/tests/aggwrap-test.d \
./src/rapidnet/tests/blowfish-encryption-test.d \
./src/rapidnet/tests/database-test.d \
./src/rapidnet/tests/evp-key-test.d \
./src/rapidnet/tests/expression-test.d \
./src/rapidnet/tests/functions-test.d \
./src/rapidnet/tests/header-test.d \
./src/rapidnet/tests/heap-relation-test.d \
./src/rapidnet/tests/join-project-test.d \
./src/rapidnet/tests/pki-authentication-test.d \
./src/rapidnet/tests/relation-test.d \
./src/rapidnet/tests/temp-relation-test.d \
./src/rapidnet/tests/tuple-attribute-test.d \
./src/rapidnet/tests/tuple-test.d \
./src/rapidnet/tests/utils-test.d \
./src/rapidnet/tests/values-test.d 


# Each subdirectory must supply rules for building sources it contributes
src/rapidnet/tests/%.o: ../src/rapidnet/tests/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


