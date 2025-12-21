/* Admin Panel - JavaScript */

document.addEventListener('DOMContentLoaded', function () {
    initDarkMode();
    initDeleteConfirmation();
});

// Dark Mode Toggle
function initDarkMode() {
    const toggle = document.getElementById('darkModeToggle');
    const html = document.documentElement;
    const saved = localStorage.getItem('theme');

    if (saved === 'dark' || (!saved && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
        html.setAttribute('data-theme', 'dark');
        updateIcon(true);
    }

    if (toggle) {
        toggle.addEventListener('click', function (e) {
            e.preventDefault();
            const isDark = html.getAttribute('data-theme') === 'dark';
            if (isDark) {
                html.removeAttribute('data-theme');
                localStorage.setItem('theme', 'light');
            } else {
                html.setAttribute('data-theme', 'dark');
                localStorage.setItem('theme', 'dark');
            }
            updateIcon(!isDark);
        });
    }

    function updateIcon(isDark) {
        const icon = document.querySelector('#darkModeToggle i');
        if (icon) icon.className = isDark ? 'bi bi-sun' : 'bi bi-moon-stars';
    }
}

// Delete Confirmation Modal
function initDeleteConfirmation() {
    const modal = document.getElementById('deleteModal');
    if (!modal) return;

    const bsModal = new bootstrap.Modal(modal);
    const confirmBtn = document.getElementById('confirmDeleteBtn');

    document.querySelectorAll('.btn-delete').forEach(btn => {
        btn.addEventListener('click', function (e) {
            e.preventDefault();
            confirmBtn.href = this.href;
            bsModal.show();
        });
    });
}

