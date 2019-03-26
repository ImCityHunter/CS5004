################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Course.cpp \
../Department.cpp \
../Person.cpp \
../Section.cpp \
../assignment-2-main.cpp 

OBJS += \
./Course.o \
./Department.o \
./Person.o \
./Section.o \
./assignment-2-main.o 

CPP_DEPS += \
./Course.d \
./Department.d \
./Person.d \
./Section.d \
./assignment-2-main.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -std=c++1y -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


