SOURCE_IMAGE = os.getenv("SOURCE_IMAGE", default='my.azurecr.io/tap-jumpstart/simplewebbapp-dev-default')
LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = os.getenv("NAMESPACE", default='default')
OUTPUT_TO_NULL_COMMAND = os.getenv("OUTPUT_TO_NULL_COMMAND", default=' > /dev/null ')
allow_k8s_contexts('my-k8s-context')
update_settings ( max_parallel_updates = 3 , k8s_upsert_timeout_secs = 3000 , suppress_unused_image_warnings = None )
k8s_custom_deploy(
    'simplewebapp-dev',
    apply_cmd="tanzu apps workload apply -f workload.yaml --debug --live-update" +
               " --local-path " + LOCAL_PATH +
               " --source-image " + SOURCE_IMAGE +
               " --namespace " + NAMESPACE +
               " --yes " +
               OUTPUT_TO_NULL_COMMAND +
               " && kubectl get workload simplewebapp-dev --namespace " + NAMESPACE + " -o yaml",
    delete_cmd="tanzu apps workload delete -f workload.yaml --namespace " + NAMESPACE + " --yes",
    deps=['pom.xml', './target/classes'],
    container_selector='workload',
    live_update=[
      sync('./target/classes', '/workspace/BOOT-INF/classes')
    ]
)

k8s_resource('simplewebapp-dev', port_forwards=["8080:8080"],
            extra_pod_selectors=[{'serving.knative.dev/service': 'simplewebapp-dev'}])