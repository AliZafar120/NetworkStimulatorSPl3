################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/discovery-epidemic/discovery-epidemic.olg.cpp 

CC_SRCS += \
../src/applications/discovery-epidemic/discovery-epidemic.cc 

OBJS += \
./src/applications/discovery-epidemic/discovery-epidemic.o \
./src/applications/discovery-epidemic/discovery-epidemic.olg.o 

CC_DEPS += \
./src/applications/discovery-epidemic/discovery-epidemic.d 

CPP_DEPS += \
./src/applications/discovery-epidemic/discovery-epidemic.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/discovery-epidemic/%.o: ../src/applications/discovery-epidemic/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/discovery-epidemic/%.o: ../src/applications/discovery-epidemic/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


