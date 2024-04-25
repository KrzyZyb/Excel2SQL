<!DOCTYPE html>
<html lang="en">
<body>
<div class="markdown prose w-full break-words dark:prose-invert light">
  <h1>XLS2SQL</h1>
  <p>The XLS to SQL Processor library provides functionality to map Excel files to SQL scripts that insert rows into a
    data table. It offers two endpoints for processing Excel files and generating SQL scripts.</p>
  <h2>Features</h2>
  <ul>
    <li>Process Excel files (XLS format) and generate SQL scripts for data insertion.</li>
    <li>Customize output SQL scripts with specified table names and column names.</li>
    <li>Validate Excel files and configurations to ensure data integrity.</li>
  </ul>
  <h2>Usage</h2>
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
  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java"><span class="hljs-type">Path</span> <span class="hljs-variable">inputFile</span> <span
      class="hljs-operator">=</span> Paths.get(<span class="hljs-string">"/user/Source.xls"</span>);
<span class="hljs-type">Path</span> <span class="hljs-variable">outputFile</span> <span class="hljs-operator">=</span> Paths.get(<span
        class="hljs-string">"./user/Destiny.sql"</span>);

<span class="hljs-comment">// Default processing method</span>
Xls2SqlProcessor.process(inputFile, outputFile);

<span class="hljs-comment">// Custom processing method with configuration</span>
<span class="hljs-type">OutputFileConfig</span> <span class="hljs-variable">config</span> <span
        class="hljs-operator">=</span> <span class="hljs-keyword">new</span> <span class="hljs-title class_">OutputFileConfig</span>(<span
        class="hljs-string">"CustomTableName"</span>, Arrays.asList(<span class="hljs-string">"Column1"</span>, <span
        class="hljs-string">"Column2"</span>));
Xls2SqlProcessor.process(inputFile, outputFile, config);
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
