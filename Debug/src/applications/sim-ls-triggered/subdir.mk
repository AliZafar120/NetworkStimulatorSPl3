################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/sim-ls-triggered/sim-ls-triggered.olg.cpp 

CC_SRCS += \
../src/applications/sim-ls-triggered/sim-ls-triggered.cc 

OBJS += \
./src/applications/sim-ls-triggered/sim-ls-triggered.o \
./src/applications/sim-ls-triggered/sim-ls-triggered.olg.o 

CC_DEPS += \
./src/applications/sim-ls-triggered/sim-ls-triggered.d 

CPP_DEPS += \
./src/applications/sim-ls-triggered/sim-ls-triggered.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/sim-ls-triggered/%.o: ../src/applications/sim-ls-triggered/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/sim-ls-triggered/%.o: ../src/applications/sim-ls-triggered/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


