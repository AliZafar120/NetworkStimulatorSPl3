################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/applications/pathvector-prov/pathvector-prov.olg.cpp 

CC_SRCS += \
../src/applications/pathvector-prov/pathvector-prov.cc 

OBJS += \
./src/applications/pathvector-prov/pathvector-prov.o \
./src/applications/pathvector-prov/pathvector-prov.olg.o 

CC_DEPS += \
./src/applications/pathvector-prov/pathvector-prov.d 

CPP_DEPS += \
./src/applications/pathvector-prov/pathvector-prov.olg.d 


# Each subdirectory must supply rules for building sources it contributes
src/applications/pathvector-prov/%.o: ../src/applications/pathvector-prov/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/applications/pathvector-prov/%.o: ../src/applications/pathvector-prov/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


