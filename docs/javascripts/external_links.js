// Otevři externí odkazy v novém tabu
document.addEventListener('DOMContentLoaded', function () {
    var links = document.querySelectorAll('a[href^="http"]');
    links.forEach(function (link) {
        // Pouze externí odkazy (ne lokální)
        if (!link.href.includes(window.location.hostname)) {
            link.setAttribute('target', '_blank');
            link.setAttribute('rel', 'noopener noreferrer');
        }
    });
});
