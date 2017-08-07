################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/emu-ls-periodic/emu-ls-periodic.olg.cpp 

CC_SRCS += \
../src/applications/emu-ls-periodic/emu-ls-periodic.cc 

OBJS += \
./src/applications/emu-ls-periodic/emu-ls-periodic.o \
./src/applications/emu-ls-periodic/emu-ls-periodic.olg.o 

CC_DEPS += \
./src/applications/emu-ls-periodic/emu-ls-periodic.d 

CPP_DEPS += \
./src/applications/emu-ls-periodic/emu-ls-periodic.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/emu-ls-periodic/%.o: ../src/applications/emu-ls-periodic/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/emu-ls-periodic/%.o: ../src/applications/emu-ls-periodic/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


