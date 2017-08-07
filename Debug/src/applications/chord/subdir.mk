################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/chord/chord.olg.cpp 

CC_SRCS += \
../src/applications/chord/chord.cc 

OBJS += \
./src/applications/chord/chord.o \
./src/applications/chord/chord.olg.o 

CC_DEPS += \
./src/applications/chord/chord.d 

CPP_DEPS += \
./src/applications/chord/chord.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/chord/%.o: ../src/applications/chord/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/chord/%.o: ../src/applications/chord/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


