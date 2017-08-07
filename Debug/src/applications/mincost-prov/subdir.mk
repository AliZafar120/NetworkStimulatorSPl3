################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/mincost-prov/mincost-prov.olg.cpp 

CC_SRCS += \
../src/applications/mincost-prov/mincost-prov.cc 

OBJS += \
./src/applications/mincost-prov/mincost-prov.o \
./src/applications/mincost-prov/mincost-prov.olg.o 

CC_DEPS += \
./src/applications/mincost-prov/mincost-prov.d 

CPP_DEPS += \
./src/applications/mincost-prov/mincost-prov.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/mincost-prov/%.o: ../src/applications/mincost-prov/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/mincost-prov/%.o: ../src/applications/mincost-prov/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


