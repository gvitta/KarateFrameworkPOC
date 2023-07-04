function fn() {
    var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev'; // a custom 'intelligent' default
    }
    var config = { // base config JSON
        cwaBaseUrl: 'https://www.amazon.co.uk/',
        githubUrlBase: 'https://github.com/login'
    };
    if (env === 'dev') {
        // over-ride only those that need to be
        config.amazonUrlBase = 'https://www.amazon.co.uk/'
        config.githubUrlBase = 'https://github.com/login'
    } else if (env === 'e2e') {
        config.amazonUrlBase = 'https://www.amazon.co.uk/'
        config.githubUrlBase = 'https://github.com/login'
    }
    // don't waste time waiting for a connection or if servers don't respond within 5 seconds
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return config;
}