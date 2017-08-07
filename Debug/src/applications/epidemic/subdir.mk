################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/epidemic/epidemic.olg.cpp 

CC_SRCS += \
../src/applications/epidemic/epidemic.cc 

OBJS += \
./src/applications/epidemic/epidemic.o \
./src/applications/epidemic/epidemic.olg.o 

CC_DEPS += \
./src/applications/epidemic/epidemic.d 

CPP_DEPS += \
./src/applications/epidemic/epidemic.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/epidemic/%.o: ../src/applications/epidemic/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/epidemic/%.o: ../src/applications/epidemic/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


