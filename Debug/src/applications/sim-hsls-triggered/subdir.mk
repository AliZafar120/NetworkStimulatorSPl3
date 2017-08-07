################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/sim-hsls-triggered/sim-hsls-triggered.olg.cpp 

CC_SRCS += \
../src/applications/sim-hsls-triggered/sim-hsls-triggered.cc 

OBJS += \
./src/applications/sim-hsls-triggered/sim-hsls-triggered.o \
./src/applications/sim-hsls-triggered/sim-hsls-triggered.olg.o 

CC_DEPS += \
./src/applications/sim-hsls-triggered/sim-hsls-triggered.d 

CPP_DEPS += \
./src/applications/sim-hsls-triggered/sim-hsls-triggered.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/sim-hsls-triggered/%.o: ../src/applications/sim-hsls-triggered/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/sim-hsls-triggered/%.o: ../src/applications/sim-hsls-triggered/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


