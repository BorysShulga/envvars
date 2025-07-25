#!/bin/bash

# AEM Environment Variables Servlet - Installation Verification Script

echo "=== AEM Environment Variables Servlet Verification ==="
echo

# Configuration
AEM_HOST=${AEM_HOST:-localhost}
AEM_PORT=${AEM_PORT:-4502}
AEM_USER=${AEM_USER:-admin}
AEM_PASS=${AEM_PASS:-admin}

SERVLET_URL="http://${AEM_HOST}:${AEM_PORT}/bin/environment-variables"
BUNDLE_CONSOLE_URL="http://${AEM_HOST}:${AEM_PORT}/system/console/bundles"

echo "Testing AEM instance at: ${AEM_HOST}:${AEM_PORT}"
echo "Servlet URL: ${SERVLET_URL}"
echo

# Test 1: Check if AEM is running
echo "1. Testing AEM connectivity..."
if curl -s --connect-timeout 5 "http://${AEM_HOST}:${AEM_PORT}" >/dev/null; then
    echo "   ✓ AEM is accessible"
else
    echo "   ✗ AEM is not accessible. Please ensure AEM is running on ${AEM_HOST}:${AEM_PORT}"
    exit 1
fi

# Test 2: Check if servlet responds
echo "2. Testing servlet endpoint..."
HTTP_CODE=$(curl -s -w "%{http_code}" -o /dev/null "${SERVLET_URL}")
if [ "$HTTP_CODE" = "200" ]; then
    echo "   ✓ Servlet is responding (HTTP 200)"
else
    echo "   ✗ Servlet not accessible (HTTP ${HTTP_CODE})"
    echo "   Please check if the bundle is installed and active."
    echo "   Bundle Console: ${BUNDLE_CONSOLE_URL}"
    exit 1
fi

# Test 3: Check servlet content
echo "3. Testing servlet content..."
CONTENT=$(curl -s "${SERVLET_URL}")
if echo "$CONTENT" | grep -q "Environment Variables"; then
    echo "   ✓ Servlet returns expected content"
else
    echo "   ✗ Servlet content appears incorrect"
    exit 1
fi

# Test 4: Check for environment variables
if echo "$CONTENT" | grep -q "PATH"; then
    echo "   ✓ Environment variables are being displayed"
else
    echo "   ⚠ Warning: PATH environment variable not found in output"
fi

echo
echo "=== Verification Complete ==="
echo "✓ All tests passed! The servlet is working correctly."
echo
echo "Access the servlet at: ${SERVLET_URL}"
echo "Bundle Console: ${BUNDLE_CONSOLE_URL}"
echo

# Optional: Display some statistics
echo "Additional Information:"
VAR_COUNT=$(echo "$CONTENT" | grep -o "Total Variables: [0-9]*" | grep -o "[0-9]*")
if [ -n "$VAR_COUNT" ]; then
    echo "   Environment Variables Found: ${VAR_COUNT}"
fi

JAVA_VERSION=$(echo "$CONTENT" | grep -o "Java Version: [^<]*" | sed 's/Java Version: //')
if [ -n "$JAVA_VERSION" ]; then
    echo "   Java Version: ${JAVA_VERSION}"
fi