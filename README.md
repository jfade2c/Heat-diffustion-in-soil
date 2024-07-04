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

MIT © 2024  Augustin Debacq

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
