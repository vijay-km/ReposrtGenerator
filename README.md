# ReportGenerator

## Overview

`ReportGenerator` is a backend microservice designed for uploading files, processing data, and generating reports. This application uses Spring Boot and an embedded H2 database for development and testing purposes.

## Getting Started

### Prerequisites

- **Java 17** (or higher)
- **Maven** (for building and running the project)

### Clone the Repository

Clone the repository to your local machine:


git clone https://github.com/vijay-km/ReportGenerator.git
cd ReportGenerator/accounts

### Scheduler Log
The scheduler log demonstrates the process of handling input and output files from our local directory. This log provides insights into the file processing and scheduling activities.
![Scheduler Log](https://github.com/vijay-km/ReposrtGenerator/blob/master/accounts/assets/SchedulerRportGenerationlog.png)

### Upload File API
The Upload File API allows users to upload files directly to the server. Once the file is uploaded, the Generate Report API can be invoked to produce the desired report.
![Upload File](https://github.com/vijay-km/ReposrtGenerator/blob/master/accounts/assets/upload.png)
![Generate Report]([[https://github.com/vijay-km/ReposrtGenerator/blob/master/accounts/assets/generateReport.png]](https://github.com/vijay-km/ReposrtGenerator/blob/master/accounts/assets/generateRe%5Bort.png))

### Future Enhancements
- **Factory Design Pattern**: The current implementation uses the Factory Design Pattern, which allows for easy extension in the future. We plan to support additional file formats such as Excel and JSON.
- **Handling Large Data**: To handle large datasets efficiently, we will implement processing in chunks. This approach will help avoid memory issues and ensure smooth application performance.
