################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/emu-hsls-periodic/emu-hsls-periodic.olg.cpp 

CC_SRCS += \
../src/applications/emu-hsls-periodic/emu-hsls-periodic.cc 

OBJS += \
./src/applications/emu-hsls-periodic/emu-hsls-periodic.o \
./src/applications/emu-hsls-periodic/emu-hsls-periodic.olg.o 

CC_DEPS += \
./src/applications/emu-hsls-periodic/emu-hsls-periodic.d 

CPP_DEPS += \
./src/applications/emu-hsls-periodic/emu-hsls-periodic.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/emu-hsls-periodic/%.o: ../src/applications/emu-hsls-periodic/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/emu-hsls-periodic/%.o: ../src/applications/emu-hsls-periodic/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


