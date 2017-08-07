################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../src/common/buffer.cc \
../src/common/byte-tag-list.cc \
../src/common/chunk.cc \
../src/common/data-rate.cc \
../src/common/error-model.cc \
../src/common/header.cc \
../src/common/packet-metadata-test.cc \
../src/common/packet-metadata.cc \
../src/common/packet-tag-list.cc \
../src/common/packet.cc \
../src/common/pcap-writer.cc \
../src/common/tag-buffer.cc \
../src/common/tag.cc \
../src/common/trailer.cc 

OBJS += \
./src/common/buffer.o \
./src/common/byte-tag-list.o \
./src/common/chunk.o \
./src/common/data-rate.o \
./src/common/error-model.o \
./src/common/header.o \
./src/common/packet-metadata-test.o \
./src/common/packet-metadata.o \
./src/common/packet-tag-list.o \
./src/common/packet.o \
./src/common/pcap-writer.o \
./src/common/tag-buffer.o \
./src/common/tag.o \
./src/common/trailer.o 

CC_DEPS += \
./src/common/buffer.d \
./src/common/byte-tag-list.d \
./src/common/chunk.d \
./src/common/data-rate.d \
./src/common/error-model.d \
./src/common/header.d \
./src/common/packet-metadata-test.d \
./src/common/packet-metadata.d \
./src/common/packet-tag-list.d \
./src/common/packet.d \
./src/common/pcap-writer.d \
./src/common/tag-buffer.d \
./src/common/tag.d \
./src/common/trailer.d 


# Each subdirectory must supply rules for building sources it contributes
src/common/%.o: ../src/common/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


