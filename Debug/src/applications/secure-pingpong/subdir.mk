################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/secure-pingpong/secure-pingpong.olg.cpp 

CC_SRCS += \
../src/applications/secure-pingpong/secure-pingpong.cc 

OBJS += \
./src/applications/secure-pingpong/secure-pingpong.o \
./src/applications/secure-pingpong/secure-pingpong.olg.o 

CC_DEPS += \
./src/applications/secure-pingpong/secure-pingpong.d 

CPP_DEPS += \
./src/applications/secure-pingpong/secure-pingpong.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/secure-pingpong/%.o: ../src/applications/secure-pingpong/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/secure-pingpong/%.o: ../src/applications/secure-pingpong/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


