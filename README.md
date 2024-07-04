# 1-Dimensional Heat Diffusion Modelling Java Program

## Overview
This Java program models heat diffusion in soil. Given two out of the three variables (duration of the process, depth at which the problem is considered, or the temperature at said depth), the program computes the third variable. This tool is essential for understanding and predicting thermal behaviors in soil environments, which can be crucial for various scientific and engineering applications.

## Features
- **Input Variables**: Users provide two of the following:
  - Duration of the process
  - Depth in the soil
  - Temperature at the specified depth
- **Output Variable**: The program calculates the third variable based on the provided inputs.
- **Free to Use**: This program is free to use under the specified terms below.

## Usage
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/jfade2c/Heat-diffustion-in-soil.git

2. **Files managing**:
   The files provided are the src (source) files. They are the only necessary ones.
   They can be ordered by packages :
   - domain logic package : U, Depth, Temperature, Time - Computing each variable and the values of the erf(x) function
   - gui package : DepthPanel, FullPanel, ThreeDimPanel, TempPanel, TwoDimPanel, TimePanel, Window, WelcomePanel
     - Displaying the first message, then the specialized panel for the corresponding variable and the 2/3D models
   - application package : Main - "Start button"

## Contributing

Feel free to enhance this program in any way.

## License

MIT © Augustin Debacq
