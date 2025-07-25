package com.example.aem.servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * Servlet that displays environment variables in a formatted HTML page.
 * Accessible at /bin/environment-variables
 */
@Component(service = Servlet.class,
    property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/environment-variables"
    })
public class EnvironmentVariablesServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EnvironmentVariablesServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {

        LOG.info("Environment Variables Servlet accessed");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        // Get all environment variables
        Map<String, String> envVars = System.getenv();
        // Sort them alphabetically for better display
        Map<String, String> sortedEnvVars = new TreeMap<>(envVars);

        // Generate HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <title>Environment Variables - AEM</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        out.println("            margin: 0;");
        out.println("            padding: 20px;");
        out.println("            background-color: #f5f5f5;");
        out.println("        }");
        out.println("        .container {");
        out.println("            max-width: 1200px;");
        out.println("            margin: 0 auto;");
        out.println("            background-color: white;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);");
        out.println("            overflow: hidden;");
        out.println("        }");
        out.println("        .header {");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            color: white;");
        out.println("            padding: 30px;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .header h1 {");
        out.println("            margin: 0;");
        out.println("            font-size: 2.5rem;");
        out.println("            font-weight: 300;");
        out.println("        }");
        out.println("        .header p {");
        out.println("            margin: 10px 0 0 0;");
        out.println("            opacity: 0.9;");
        out.println("        }");
        out.println("        .content {");
        out.println("            padding: 30px;");
        out.println("        }");
        out.println("        .stats {");
        out.println("            display: flex;");
        out.println("            justify-content: space-around;");
        out.println("            margin-bottom: 30px;");
        out.println("            padding: 20px;");
        out.println("            background-color: #f8f9fa;");
        out.println("            border-radius: 6px;");
        out.println("        }");
        out.println("        .stat-item {");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .stat-number {");
        out.println("            font-size: 2rem;");
        out.println("            font-weight: bold;");
        out.println("            color: #667eea;");
        out.println("        }");
        out.println("        .stat-label {");
        out.println("            color: #666;");
        out.println("            font-size: 0.9rem;");
        out.println("        }");
        out.println("        .search-box {");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .search-box input {");
        out.println("            width: 100%;");
        out.println("            padding: 12px;");
        out.println("            border: 2px solid #e1e1e1;");
        out.println("            border-radius: 6px;");
        out.println("            font-size: 16px;");
        out.println("            box-sizing: border-box;");
        out.println("        }");
        out.println("        .search-box input:focus {");
        out.println("            outline: none;");
        out.println("            border-color: #667eea;");
        out.println("        }");
        out.println("        .env-table {");
        out.println("            width: 100%;");
        out.println("            border-collapse: collapse;");
        out.println("            margin-top: 20px;");
        out.println("        }");
        out.println("        .env-table th {");
        out.println("            background-color: #667eea;");
        out.println("            color: white;");
        out.println("            padding: 15px;");
        out.println("            text-align: left;");
        out.println("            font-weight: 600;");
        out.println("        }");
        out.println("        .env-table td {");
        out.println("            padding: 12px 15px;");
        out.println("            border-bottom: 1px solid #e1e1e1;");
        out.println("            vertical-align: top;");
        out.println("        }");
        out.println("        .env-table tr:hover {");
        out.println("            background-color: #f8f9fa;");
        out.println("        }");
        out.println("        .env-key {");
        out.println("            font-family: 'Courier New', monospace;");
        out.println("            font-weight: bold;");
        out.println("            color: #333;");
        out.println("            min-width: 200px;");
        out.println("        }");
        out.println("        .env-value {");
        out.println("            font-family: 'Courier New', monospace;");
        out.println("            word-break: break-all;");
        out.println("            color: #555;");
        out.println("        }");
        out.println("        .footer {");
        out.println("            text-align: center;");
        out.println("            padding: 20px;");
        out.println("            color: #666;");
        out.println("            font-size: 0.9rem;");
        out.println("            border-top: 1px solid #e1e1e1;");
        out.println("            margin-top: 30px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class=\"container\">");
        out.println("        <div class=\"header\">");
        out.println("            <h1>Environment Variables</h1>");
        out.println("            <p>Adobe Experience Manager - System Environment</p>");
        out.println("        </div>");
        out.println("        <div class=\"content\">");
        
        // Statistics
        out.println("            <div class=\"stats\">");
        out.println("                <div class=\"stat-item\">");
        out.println("                    <div class=\"stat-number\">" + sortedEnvVars.size() + "</div>");
        out.println("                    <div class=\"stat-label\">Total Variables</div>");
        out.println("                </div>");
        out.println("                <div class=\"stat-item\">");
        out.println("                    <div class=\"stat-number\">" + System.getProperty("java.version") + "</div>");
        out.println("                    <div class=\"stat-label\">Java Version</div>");
        out.println("                </div>");
        out.println("                <div class=\"stat-item\">");
        out.println("                    <div class=\"stat-number\">" + System.getProperty("os.name") + "</div>");
        out.println("                    <div class=\"stat-label\">Operating System</div>");
        out.println("                </div>");
        out.println("            </div>");

        // Search box
        out.println("            <div class=\"search-box\">");
        out.println("                <input type=\"text\" id=\"searchInput\" placeholder=\"Search environment variables...\" onkeyup=\"filterTable()\">");
        out.println("            </div>");

        // Environment variables table
        out.println("            <table class=\"env-table\" id=\"envTable\">");
        out.println("                <thead>");
        out.println("                    <tr>");
        out.println("                        <th>Variable Name</th>");
        out.println("                        <th>Value</th>");
        out.println("                    </tr>");
        out.println("                </thead>");
        out.println("                <tbody>");

        for (Map.Entry<String, String> entry : sortedEnvVars.entrySet()) {
            String key = escapeHtml(entry.getKey());
            String value = escapeHtml(entry.getValue());
            
            out.println("                    <tr>");
            out.println("                        <td class=\"env-key\">" + key + "</td>");
            out.println("                        <td class=\"env-value\">" + value + "</td>");
            out.println("                    </tr>");
        }

        out.println("                </tbody>");
        out.println("            </table>");
        out.println("        </div>");
        out.println("        <div class=\"footer\">");
        out.println("            <p>Generated on " + new java.util.Date() + " | Adobe Experience Manager</p>");
        out.println("        </div>");
        out.println("    </div>");

        // JavaScript for search functionality
        out.println("    <script>");
        out.println("        function filterTable() {");
        out.println("            var input, filter, table, tr, td, i, txtValue;");
        out.println("            input = document.getElementById('searchInput');");
        out.println("            filter = input.value.toUpperCase();");
        out.println("            table = document.getElementById('envTable');");
        out.println("            tr = table.getElementsByTagName('tr');");
        out.println("            for (i = 1; i < tr.length; i++) {");
        out.println("                td = tr[i].getElementsByTagName('td')[0];");
        out.println("                if (td) {");
        out.println("                    txtValue = td.textContent || td.innerText;");
        out.println("                    if (txtValue.toUpperCase().indexOf(filter) > -1) {");
        out.println("                        tr[i].style.display = '';");
        out.println("                    } else {");
        out.println("                        tr[i].style.display = 'none';");
        out.println("                    }");
        out.println("                }");
        out.println("            }");
        out.println("        }");
        out.println("    </script>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
    }

    /**
     * Escape HTML special characters to prevent XSS
     */
    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;");
    }
}