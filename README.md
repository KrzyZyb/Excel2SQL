![Static Badge](https://img.shields.io/badge/License-MIT-yellow?link=https%3A%2F%2Fopensource.org%2Flicense%2FMIT)
[![Build](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven.yml/badge.svg)](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven.yml)
[![Package Deploy](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven-publish.yml)
![GitHub Release](https://img.shields.io/github/v/release/KrzyZyb/XLS2SQL)











<!DOCTYPE html>
<html lang="en">
<body>
<div class="markdown prose w-full break-words dark:prose-invert light">
  <h1>Excel2SQL</h1>
  <p>The Excel to SQL Processor library provides functionality to map Excel files to SQL scripts that insert rows into a
    data table. It offers two endpoints for processing Excel files and generating SQL scripts.</p>
  <h2>Features</h2>
  <ul>
    <li>Process Excel files (XLS format) and generate SQL scripts for data insertion.</li>
    <li>Customize output SQL scripts with specified table names and column names.</li>
    <li>Validate Excel files and configurations to ensure data integrity.</li>
  </ul>
  <h2>Usage</h2>
    <h3>Dependency</h3>
  <p>Just use as regular dependency via maven in POM.xml</p>
    <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">  
&lt;dependency&gt;
  &lt;groupId&gt;io.github.krzyzyb&lt;/groupId&gt;
  &lt;artifactId&gt;xls2sql&lt;/artifactId&gt;
  &lt;version&gt;1.1.3&lt;/version&gt;
&lt;/dependency&gt;

</code></div></div></pre>

  <p>Don't forget to inform maven that you are using dependencies from github package registry. Update POM.xml with:</p>

  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">  
&lt;repositories&gt;
  &lt;repository&gt;
    &lt;id&gt;github&lt;/id&gt;
    &lt;url&gt;https://maven.pkg.github.com/krzyzyb/xls2sql&lt;/url&gt;
  &lt;/repository&gt;
&lt;/repositories&gt;

</code></div></div></pre>

  <h3>Default Processing Method</h3>
  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">Xls2SqlProcessor.process(Path inputFile, Path outputFile)
</code></div></div></pre>
  <p>In the default processing method, <code>Xls2SqlProcessor.process</code>, provide the path to the input Excel file (<code>inputFile</code>)
    containing the data to insert and the path to the output SQL script file (<code>outputFile</code>). The library
    automatically generates an SQL script with column names extracted from the XLS header and the table name derived
    from the output file name.</p>
  <h3>Custom Processing Method</h3>
  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">Xls2SqlProcessor.process(Path inputFile, Path outputFile, OutputFileConfig config)
</code></div></div></pre>
  <p>In the custom processing method, <code>Xls2SqlProcessor.process</code>, provide the path to the input Excel file
    (<code>inputFile</code>), the path to the output SQL script file (<code>outputFile</code>), and a custom
    configuration (<code>config</code>). The configuration (<code>OutputFileConfig</code>) allows you to customize the
    table name and column names for the output SQL script.</p>
  <h2>Example</h2>
  <div><code>Path inputFile = Paths.get("./user/Source.xls");</code></div>
  <div><code>Path outputFile = Paths.get("./user/Destiny.sql");</code></div>
<br>
<div>// Default processing method</div>
<div><code>Xls2SqlProcessor.process(inputFile, outputFile);</code></div>
<br>
<div>// Custom processing method with configuration</div>
<div><code>OutputFileConfig config = new OutputFileConfig("CustomTableName", Arrays.asList("Column1", "Column2"));</code></div>
<div><code>Xls2SqlProcessor.process(inputFile, outputFile, config);</code></div>
</code></div></div></pre>
  <h2>Requirements</h2>
  <ul>
    <li>Java 8 or higher</li>
  </ul>
  <h2>License</h2>
  <p>This library is licensed under the MIT License.</p>
</div>
</body>
</html>
