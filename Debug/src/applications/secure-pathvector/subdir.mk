################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/secure-pathvector/secure-pathvector.olg.cpp 

CC_SRCS += \
../src/applications/secure-pathvector/secure-pathvector.cc 

OBJS += \
./src/applications/secure-pathvector/secure-pathvector.o \
./src/applications/secure-pathvector/secure-pathvector.olg.o 

CC_DEPS += \
./src/applications/secure-pathvector/secure-pathvector.d 

CPP_DEPS += \
./src/applications/secure-pathvector/secure-pathvector.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/secure-pathvector/%.o: ../src/applications/secure-pathvector/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/secure-pathvector/%.o: ../src/applications/secure-pathvector/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


