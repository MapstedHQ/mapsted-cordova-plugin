#!/usr/bin/env node

var fs = require('fs');
var path = require('path');

module.exports = function(context) {
    var gradleFile = path.join(context.opts.projectRoot, 'platforms', 'android', 'app', 'repositories.gradle');
    var jitpackUrl = 'maven { url "https://jitpack.io" }';
    var mapstedUrl = 'maven { url "https://mapstedhq.github.io/mapsted-android-maven" }';
    var reposBlockEnd = '}';

    // Check if the repositories.gradle file exists
    if (fs.existsSync(gradleFile)) {
        // Read the content of repositories.gradle
        var content = fs.readFileSync(gradleFile, 'utf8');

        // Check if the URLs are already present
        var jitpackIndex = content.indexOf(jitpackUrl);
        var mapstedIndex = content.indexOf(mapstedUrl);

        // If URLs are not present, append them
        if (jitpackIndex === -1 || mapstedIndex === -1) {
            // Find the index of the end of ext.repos block
            var reposBlockIndex = content.indexOf('ext.repos = {');
            var reposBlockEndIndex = content.indexOf(reposBlockEnd, reposBlockIndex);

            // Construct the string to append
            var appendText = '';
            if (jitpackIndex === -1) {
                appendText += '\n\t' + jitpackUrl;
            }
            if (mapstedIndex === -1) {
                appendText += '\n\t' + mapstedUrl;
            }

            // Insert the appendText at the end of ext.repos block
            if (reposBlockIndex !== -1 && reposBlockEndIndex !== -1) {
                var insertIndex = reposBlockEndIndex - 1;
                content = content.slice(0, insertIndex) + appendText + content.slice(insertIndex);
            }
        }

        // Write the updated content back to repositories.gradle
        fs.writeFileSync(gradleFile, content, 'utf8');
    }
};
