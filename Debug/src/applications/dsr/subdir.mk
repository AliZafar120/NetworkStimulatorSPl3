################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/dsr/dsr.olg.cpp 

CC_SRCS += \
../src/applications/dsr/dsr.cc 

OBJS += \
./src/applications/dsr/dsr.o \
./src/applications/dsr/dsr.olg.o 

CC_DEPS += \
./src/applications/dsr/dsr.d 

CPP_DEPS += \
./src/applications/dsr/dsr.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/dsr/%.o: ../src/applications/dsr/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/dsr/%.o: ../src/applications/dsr/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


