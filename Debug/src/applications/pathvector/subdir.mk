################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/pathvector/pathvector.olg.cpp 

CC_SRCS += \
../src/applications/pathvector/pathvector.cc 

OBJS += \
./src/applications/pathvector/pathvector.o \
./src/applications/pathvector/pathvector.olg.o 

CC_DEPS += \
./src/applications/pathvector/pathvector.d 

CPP_DEPS += \
./src/applications/pathvector/pathvector.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/pathvector/%.o: ../src/applications/pathvector/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/pathvector/%.o: ../src/applications/pathvector/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


