################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/pktfwd-prov/pktfwd-prov.olg.cpp 

CC_SRCS += \
../src/applications/pktfwd-prov/pktfwd-prov.cc 

OBJS += \
./src/applications/pktfwd-prov/pktfwd-prov.o \
./src/applications/pktfwd-prov/pktfwd-prov.olg.o 

CC_DEPS += \
./src/applications/pktfwd-prov/pktfwd-prov.d 

CPP_DEPS += \
./src/applications/pktfwd-prov/pktfwd-prov.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/pktfwd-prov/%.o: ../src/applications/pktfwd-prov/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/pktfwd-prov/%.o: ../src/applications/pktfwd-prov/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


