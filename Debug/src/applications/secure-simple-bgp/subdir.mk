################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/secure-simple-bgp/secure-simple-bgp.olg.cpp 

CC_SRCS += \
../src/applications/secure-simple-bgp/secure-simple-bgp.cc 

OBJS += \
./src/applications/secure-simple-bgp/secure-simple-bgp.o \
./src/applications/secure-simple-bgp/secure-simple-bgp.olg.o 

CC_DEPS += \
./src/applications/secure-simple-bgp/secure-simple-bgp.d 

CPP_DEPS += \
./src/applications/secure-simple-bgp/secure-simple-bgp.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/secure-simple-bgp/%.o: ../src/applications/secure-simple-bgp/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/secure-simple-bgp/%.o: ../src/applications/secure-simple-bgp/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


