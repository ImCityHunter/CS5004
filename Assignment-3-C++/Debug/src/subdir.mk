################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Circle2D.cpp \
../src/Line2D.cpp \
../src/Point2D.cpp \
../src/Rectangle2D.cpp \
../src/Shape2D.cpp \
../src/TestShapes.cpp 

OBJS += \
./src/Circle2D.o \
./src/Line2D.o \
./src/Point2D.o \
./src/Rectangle2D.o \
./src/Shape2D.o \
./src/TestShapes.o 

CPP_DEPS += \
./src/Circle2D.d \
./src/Line2D.d \
./src/Point2D.d \
./src/Rectangle2D.d \
./src/Shape2D.d \
./src/TestShapes.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -std=c++1y -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


