
(require 'package)
(let* ((no-ssl (and (memq system-type '(windows-nt ms-dos))
                    (not (gnutls-available-p))))
       (proto (if no-ssl "http" "https")))
  (when no-ssl
    (warn "\
Your version of Emacs does not support SSL connections,
which is unsafe because it allows man-in-the-middle attacks.
There are two things you can do about this warning:
1. Install an Emacs version that does support SSL and be safe.
2. Remove this warning from your init file so you won't see it again."))
  ;; Comment/uncomment these two lines to enable/disable MELPA and MELPA Stable as desired
  (add-to-list 'package-archives (cons "melpa" (concat proto "://melpa.org/packages/")) t)
  ;;(add-to-list 'package-archives (cons "melpa-stable" (concat proto "://stable.melpa.org/packages/")) t)
  (when (< emacs-major-version 24)
    ;; For important compatibility libraries like cl-lib
    (add-to-list 'package-archives (cons "gnu" (concat proto "://elpa.gnu.org/packages/")))))
(package-initialize)
(add-to-list 'package-archives
             '("melpa-stable" . "https://stable.melpa.org/packages/") t)
(custom-set-variables
 ;; custom-set-variables was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(eclimd-executable "/home/ddw/.eclipse/360744347_linux_gtk_x86_64/eclimd")
 '(elpy-rpc-python-command "python3")
 '(elpy-syntax-check-command "/home/dd1/.local/bin/flake8")
 '(inhibit-default-init nil)
 '(inhibit-startup-screen t)
 '(initial-scratch-message nil)
 '(package-selected-packages
   (quote
    (jenkinsfile-mode docker transient docker-compose-mode docker-cli dotnet logstash-conf reverse-im flymake ssh-agency markdown-mode terraform-doc scala-mode csv-mode ansible company-ansible yaml-mode org-agenda-property ivy ivy-hydra counsel org-link-minor-mode emacsql-mysql company-web web-mode mmm-mode crappy-jsp-mode rainbow-delimiters company-terraform terraform-mode ac-js2 company-tern xref-js2 js2-refactor js2-mode flymake-jslint flycheck highlight-parentheses json-mode docker-api dockerfile-mode magit diffview git-commit git-command git groovy-mode jenkins-watch jenkins bash-completion yapfify elpygen elpy smartparens jedi auto-complete paredit eclim zenburn-theme)))
 '(python-shell-interpreter "python3"))
(custom-set-faces
 ;; custom-set-faces was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(rainbow-delimiters-depth-2-face ((t (:foreground "indian red"))))
 '(rainbow-delimiters-depth-3-face ((t (:foreground "chocolate"))))
 '(rainbow-delimiters-depth-4-face ((t (:foreground "light goldenrod"))))
 '(rainbow-delimiters-depth-5-face ((t (:foreground "green"))))
 '(rainbow-delimiters-depth-6-face ((t (:foreground "deep sky blue"))))
 '(rainbow-delimiters-depth-7-face ((t (:foreground "medium blue"))))
 '(rainbow-delimiters-depth-8-face ((t (:foreground "magenta4"))))
 '(rainbow-delimiters-depth-9-face ((t (:foreground "tan4")))))

;;(global-display-line-numbers-mode)

(load-theme 'zenburn t)
(menu-bar-mode -1)
(toggle-scroll-bar -1)
(tool-bar-mode -1)



					;(add-hook 'python-mode-hook 'jedi:setup)
(add-hook 'python-mode-hook 'smartparens-mode)
					;(setq jedi:complete-on-dot t) 

(package-initialize)
(elpy-enable)
(put 'upcase-region 'disabled nil)


					;backup files settings
(defvar --backup-directory (concat user-emacs-directory "backups"))
(if (not (file-exists-p --backup-directory))
    (make-directory --backup-directory t))
(setq backup-directory-alist `(("." . ,--backup-directory)))
(setq make-backup-files t               ; backup of a file the first time it is saved.
      backup-by-copying t               ; don't clobber symlinks
      version-control t                 ; version numbers for backup files
      delete-old-versions t             ; delete excess backup files silently
      delete-by-moving-to-trash t
      kept-old-versions 6               ; oldest versions to keep when a new numbered backup is made (default: 2)
      kept-new-versions 9               ; newest versions to keep when a new numbered backup is made (default: 2)
      auto-save-default t               ; auto-save every buffer that visits a file
      auto-save-timeout 20              ; number of seconds idle time before auto-save (default: 30)
      auto-save-interval 200            ; number of keystrokes between auto-saves (default: 300)
      )

					;enable ido-mode
(setq ido-enable-flex-matching t)
(setq ido-everywhere t)
(ido-mode 1)

;;set custom key bindings
(global-set-key (kbd "C-x g") 'magit-status)
(global-set-key (kbd "C-x M-f") 'find-file-in-project)


;;add js support
(require 'js2-mode)
(add-to-list 'auto-mode-alist '("\\.js\\'" . js2-mode))
(add-hook 'js2-mode-hook #'js2-imenu-extras-mode)

(require 'js2-refactor)
(require 'xref-js2)

(add-hook 'js2-mode-hook #'js2-refactor-mode)
(js2r-add-keybindings-with-prefix "C-c C-r")
(define-key js2-mode-map (kbd "C-k") #'js2r-kill)

;; js-mode (which js2 is based on) binds "M-." which conflicts with xref, so
;; unbind it.
(define-key js-mode-map (kbd "M-.") nil)

(add-hook 'js2-mode-hook (lambda ()
  (add-hook 'xref-backend-functions #'xref-js2-xref-backend nil t)))
(require 'company)
(require 'company-tern)

(add-to-list 'company-backends 'company-tern)
(add-hook 'js2-mode-hook (lambda ()
                           (tern-mode)
                           (company-mode)))
                           
;; Disable completion keybindings, as we use xref-js2 instead
(define-key tern-mode-keymap (kbd "M-.") nil)
(define-key tern-mode-keymap (kbd "M-,") nil)


;; enable company for terraform
(require 'company-terraform)
(company-terraform-init)
;; add rainbow delimiters to company 
(add-hook 'company-mode-hook #'highlight-parentheses-mode)
(put 'downcase-region 'disabled nil)


;; org mode
(require 'org)
(define-key global-map "\C-cl" 'org-store-link)
(define-key global-map "\C-ca" 'org-agenda)
(setq org-log-done t)


;; change fullscreen hotkey to F9
(global-set-key [f9] 'toggle-frame-fullscreen)

;; xml lint mode
(defun mu-xml-format ()
  "Format an XML buffer with `xmllint'."
  (interactive)
  (shell-command-on-region (point-min) (point-max)
                           "xmllint -format -"
                           (current-buffer) t
                           "*Xmllint Error Buffer*" t))

;; (use-package nxml-mode                  ; XML editing
;;   :mode "\\.xml\\'"
;;   :bind (:map nxml-mode-map
;;               ("C-c m f" . mu-xml-format)))

(use-package docker
  :ensure t
  :bind ("C-c d" . docker))

;; groovy modes
(add-to-list 'auto-mode-alist '("\\.groovy\\'" . highlight-parentheses-mode) t)
(add-to-list 'auto-mode-alist '("\\.groovy\\'" . smartparens-mode) t)
