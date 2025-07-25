# AEM Environment Variables Servlet

This project provides an Adobe Experience Manager (AEM) servlet that displays system environment variables in a beautiful, responsive web interface.

## Features

- 🌐 **Web Interface**: Beautiful, responsive HTML page showing all environment variables
- 🔍 **Search Functionality**: Real-time search through environment variables
- 📊 **System Statistics**: Display of Java version, OS information, and variable count
- 🎨 **Modern UI**: Clean, professional design with gradient headers and interactive elements
- 🔒 **Security**: HTML escaping to prevent XSS attacks

## Project Structure

```
├── pom.xml                                          # Maven configuration
├── src/main/java/com/example/aem/servlet/
│   └── EnvironmentVariablesServlet.java            # Main servlet implementation
├── src/main/content/                               # Content package structure
│   ├── jcr_root/apps/example-aem/
│   └── META-INF/vault/                             # Vault package configuration
├── src/main/assembly/package.xml                   # Assembly descriptor
├── target/
│   ├── environment-variables-servlet-1.0.0-SNAPSHOT.jar           # OSGi Bundle
│   └── environment-variables-servlet-1.0.0-SNAPSHOT-aem-package.zip # Deployment Package
└── README.md                                       # This file
```

## Building the Project

### Prerequisites
- **Java 11+**: The project requires Java 11 or higher
- **Maven 3.6+**: Apache Maven for building the project

### Build Commands

1. **Clean and compile the project:**
   ```bash
   mvn clean compile
   ```

2. **Build the complete package:**
   ```bash
   mvn clean package
   ```

This will generate two important artifacts:
- `target/environment-variables-servlet-1.0.0-SNAPSHOT.jar` - OSGi bundle
- `target/environment-variables-servlet-1.0.0-SNAPSHOT-aem-package.zip` - Deployable package

## Deployment to AEM

### Option 1: Using the ZIP Package (Recommended)

1. **Access AEM Package Manager:**
   - Open your browser and go to: `http://localhost:4502/crx/packmgr/index.jsp`
   - Login with admin credentials (usually admin/admin)

2. **Upload and Install the Package:**
   - Click on "Upload Package"
   - Select the file: `target/environment-variables-servlet-1.0.0-SNAPSHOT-aem-package.zip`
   - Click "Upload"
   - Once uploaded, click "Install" next to the package

### Option 2: Manual Bundle Installation

1. **Access Felix Console:**
   - Go to: `http://localhost:4502/system/console/bundles`
   - Login with admin credentials

2. **Install the Bundle:**
   - Click "Install/Update"
   - Choose the file: `target/environment-variables-servlet-1.0.0-SNAPSHOT.jar`
   - Click "Install or Update"

## Usage

Once deployed, the servlet will be available at:

```
http://localhost:4502/bin/environment-variables
```

### What You'll See

The servlet displays:
- **System Information**: Java version, OS details, user information
- **Environment Variables**: Complete list of all environment variables
- **Search Functionality**: Real-time filtering of environment variables
- **Statistics**: Total count of environment variables

### Features of the Web Interface

- **Responsive Design**: Works on desktop and mobile devices
- **Real-time Search**: Type in the search box to filter environment variables
- **Professional Styling**: Modern CSS with gradients and smooth animations
- **Security**: All output is HTML-escaped to prevent security issues

## Development

### Making Changes

1. Modify the servlet code in `src/main/java/com/example/aem/servlet/EnvironmentVariablesServlet.java`
2. Rebuild with `mvn clean package`
3. Redeploy the package to AEM

### Customization Options

You can customize:
- **Servlet Path**: Change the `sling.servlet.paths` property in the `@Component` annotation
- **Styling**: Modify the CSS in the `generateCSS()` method
- **Content**: Adjust the HTML structure in the `doGet()` method
- **Security**: Add authentication/authorization as needed

## Security Considerations

- The servlet displays environment variables which may contain sensitive information
- Consider restricting access in production environments
- All HTML output is escaped to prevent XSS attacks
- The servlet runs with the permissions of the AEM service user

## Technical Details

- **Framework**: Adobe Experience Manager (AEM) / Apache Sling
- **Language**: Java 11
- **Build Tool**: Apache Maven
- **Package Type**: OSGi Bundle
- **Dependencies**: Sling API, Servlet API, OSGi annotations

## Troubleshooting

### Bundle Not Starting
- Check the Felix Console for error messages
- Verify all dependencies are satisfied
- Check AEM logs in `crx-quickstart/logs/error.log`

### Servlet Not Accessible
- Verify the bundle is in "Active" state
- Check if there are path conflicts with other servlets
- Ensure you're accessing the correct URL

### Build Issues
- Verify Java 11+ is installed: `java -version`
- Check Maven installation: `mvn -version`
- Clear Maven cache: `mvn clean`

## License

This project is provided as an example for educational purposes.

## Support

For issues or questions:
1. Check the AEM error logs
2. Verify the bundle status in Felix Console
3. Review the Maven build output for any errors