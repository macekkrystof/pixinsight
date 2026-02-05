// Otevři externí odkazy v novém tabu
document.addEventListener('DOMContentLoaded', function () {
    var links = document.querySelectorAll('a[href^="http"]');
    var currentPath = window.location.pathname;

    links.forEach(function (link) {
        // Externí odkaz = začíná http a není součástí této dokumentace (/pixinsight/)
        var isDocumentation = link.href.includes(window.location.hostname) &&
            link.pathname.startsWith('/pixinsight/');

        if (!isDocumentation) {
            link.setAttribute('target', '_blank');
            link.setAttribute('rel', 'noopener noreferrer');
        }
    });
});
